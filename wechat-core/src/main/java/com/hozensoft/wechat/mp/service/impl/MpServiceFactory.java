package com.hozensoft.wechat.mp.service.impl;

import com.hozensoft.wechat.mp.handler.*;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.constant.WxMpEventConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import me.chanjar.weixin.common.api.WxConsts.*;

@Component
public class MpServiceFactory {

    @Autowired
    private MpLogHandler logHandler;

    @Autowired
    private MpNullHandler nullHandler;

    @Autowired
    private MpKfSessionHandler kfSessionHandler;

    @Autowired
    private MpStoreCheckNotifyHandler storeCheckNotifyHandler;

    @Autowired
    private MpLocationHandler locationHandler;

    @Autowired
    private MpMenuHandler menuHandler;

    @Autowired
    private MpMsgHandler msgHandler;

    @Autowired
    private MpUnsubscribeHandler unsubscribeHandler;

    @Autowired
    private MpSubscribeHandler subscribeHandler;

    public WxMpService buildWxMpService(String appId){

        WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
        configStorage.setAppId(appId);
        configStorage.setSecret("a2aaf995b5c469afaeb47a309be202c3");
        configStorage.setToken("zhujingjun");
        configStorage.setAesKey("BeLbVs6W3Gf5FlYMgZrImGyMFvv04SecSVGHVo0LoZc");

        WxMpService service = new WxMpServiceImpl();

        service.setWxMpConfigStorage(configStorage);

        return service;
    }

    public WxMpMessageRouter buildMessageRouter(WxMpService wxMpService){

        final WxMpMessageRouter router = new WxMpMessageRouter(wxMpService);

        // 记录所有事件的日志 （异步执行）
        router.rule().handler(this.logHandler).next();

        // 接收客服会话管理事件
        router.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(WxMpEventConstants.CustomerService.KF_CREATE_SESSION)
                .handler(this.kfSessionHandler).end();
        router.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(WxMpEventConstants.CustomerService.KF_CLOSE_SESSION)
                .handler(this.kfSessionHandler)
                .end();
        router.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(WxMpEventConstants.CustomerService.KF_SWITCH_SESSION)
                .handler(this.kfSessionHandler).end();

        // 门店审核事件
        router.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(WxMpEventConstants.POI_CHECK_NOTIFY)
                .handler(this.storeCheckNotifyHandler).end();

        // 自定义菜单事件
        router.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(MenuButtonType.CLICK).handler(this.menuHandler).end();

        // 点击菜单连接事件
        router.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(MenuButtonType.VIEW).handler(this.nullHandler).end();

        // 关注事件
        router.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.SUBSCRIBE).handler(this.subscribeHandler)
                .end();

        // 取消关注事件
        router.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.UNSUBSCRIBE)
                .handler(this.unsubscribeHandler).end();

        // 上报地理位置事件
        router.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.LOCATION).handler(this.locationHandler)
                .end();

        // 接收地理位置消息
        router.rule().async(false).msgType(XmlMsgType.LOCATION)
                .handler(this.locationHandler).end();

        // 扫码事件
        router.rule().async(false).msgType(XmlMsgType.EVENT)
                .event(EventType.SCAN).handler(this.nullHandler).end();

        // 默认
        router.rule().async(false).handler(this.msgHandler).end();

        return router;
    }
}
