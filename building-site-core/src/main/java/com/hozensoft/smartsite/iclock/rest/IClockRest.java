package com.hozensoft.smartsite.iclock.rest;

import com.hozensoft.smartsite.enumeration.ClockTypeEnum;
import com.hozensoft.smartsite.enumeration.IClockUploadTypeEnum;
import com.hozensoft.smartsite.iclock.dto.IClockCommandItemDto;
import com.hozensoft.smartsite.iclock.dto.IClockRecordDto;
import com.hozensoft.smartsite.iclock.dto.IdCardClockRecordDto;
import com.hozensoft.smartsite.iclock.service.IClockCommandService;
import com.hozensoft.smartsite.iclock.service.IClockRecordService;
import com.hozensoft.utils.date.DateParseUtils;
import com.hozensoft.utils.persistent.IdGen;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/iclock")
public class IClockRest {

    public static final String KEY = "GBL_DEVICES_LIST";

    @Autowired
    private IClockRecordService iClockRecordService;

    @Autowired
    private IClockCommandService iClockCommandService;

    @GetMapping(value="/getrequest")
    public @ResponseBody void getRequest(HttpServletRequest request, HttpServletResponse response,
                                         @RequestParam(value = "SN", required = false)String serialNumber) throws IOException {

        Map<String, Date> onlineDeviceHash = (Map<String, Date>)request.getServletContext().getAttribute(KEY);

        if(onlineDeviceHash==null){
            onlineDeviceHash = new HashMap<>();
            request.getServletContext().setAttribute(KEY, onlineDeviceHash);
        }

        onlineDeviceHash.put(serialNumber, new Date());

        response.setContentType("text/html; charset=gb2312");
        response.setCharacterEncoding("gb2312");

        List<IClockCommandItemDto> commands = iClockCommandService.findIClockCommandBySn(serialNumber);

        if(CollectionUtils.isEmpty(commands)){
            response.getWriter().print("OK");
        }else{
            StringBuffer buffer = new StringBuffer();

            commands.stream().forEach((command)->{
                buffer.append(command.getCommand() + "\n");
            });

            response.getWriter().print(buffer.toString());
        }
    }

    @GetMapping("/cdata")
    public @ResponseBody void getCData(HttpServletRequest request, HttpServletResponse response,
                                       @RequestParam(value = "SN", required = false)String serialNumber,
                                       @RequestParam(value = "options", required = false)String options,
                                       @RequestParam(value = "pushver", required = false)String pushver,
                                       @RequestParam(value = "language", required = false)String languae,
                                       @RequestParam(value = "pushcommkey", required = false)String pushCommKey) throws IOException {

        response.setContentType("text/html; charset=gb2312");

        StringBuffer buffer = new StringBuffer();

        buffer.append("GET OPTION FROM: " + serialNumber + "\n");
        buffer.append("ATTLOGStamp=None" + "\n");
        buffer.append("OPERLOGStamp=9999" + "\n");
        buffer.append("ATTPHOTOStamp=None" + "\n");
        buffer.append("ErrorDelay=30" + "\n");
        buffer.append("Delay=60" + "\n");
        buffer.append("TransTimes=00:00;23:59" + "\n");
        buffer.append("TransInterval=1" + "\n");
        buffer.append("TransFlag=1001111111" + "\n");
        buffer.append("TimeZone=8" + "\n");
        buffer.append("Realtime=1" + "\n");
        buffer.append("Encrypt=None" + "\n");

        response.getWriter().print(buffer.toString());
    }

    @PostMapping("/cdata")
    public @ResponseBody void postCData(HttpServletRequest request, HttpServletResponse response,
                                          @RequestParam("SN")String serialNumber,
                                          @RequestParam("table")IClockUploadTypeEnum uploadType) throws IOException {

        response.setContentType("text/html; charset=utf-8");

        if(IClockUploadTypeEnum.ATTLOG.equals(uploadType)){
            String result = doClock(request, response, serialNumber, uploadType);

            response.getWriter().print(result);
        }

        if(IClockUploadTypeEnum.OPERLOG.equals(uploadType)){

            String strLine="";
            StringBuffer command = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));

            while((strLine = reader.readLine()) != null)
            {
                command.append(strLine + "\n");
            }

            iClockCommandService.syncCommand(serialNumber, command.toString());

            response.getWriter().print("OK");
        }

        response.getWriter().print("OK");
    }

    @PostMapping("/iccard")
    public @ResponseBody List<String> iccard(HttpServletRequest request, HttpServletResponse response,
                                     @RequestBody List<IdCardClockRecordDto> recordList){

        if(CollectionUtils.isEmpty(recordList)){
            return Collections.emptyList();
        }

        recordList.stream().forEach(item->{
            item.setType(ClockTypeEnum.CARD);
        });

        iClockRecordService.clockingWithIdCard(recordList);

        return recordList.stream().map(item->item.getId()).collect(Collectors.toList());
    }

    protected String doClock(HttpServletRequest request, HttpServletResponse response,
                             String serialNumber, IClockUploadTypeEnum uploadType) throws IOException{

        response.setContentType("text/html; charset=gb2312");

        String strLine="";
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        List<IClockRecordDto> recordList = new ArrayList<>();

        while((strLine = reader.readLine()) != null)
        {
            String[] parts = strLine.split("\t");

            IClockRecordDto record = new IClockRecordDto();

            record.setId(IdGen.generate());
            record.setSn(serialNumber);
            record.setCode(parts[0]);
            record.setClockTime(DateParseUtils.parseDate(parts[1]));
            record.setType("15".equals(parts[2])?ClockTypeEnum.FACE:ClockTypeEnum.FINGERPRINT);

            recordList.add(record);
        }

        return "OK:" + iClockRecordService.clocking(recordList);
    }

    @PostMapping(value="/devicecmd")
    public @ResponseBody void onResponse(HttpServletRequest request, HttpServletResponse response, @RequestParam("SN")String serialNumber) throws IOException {

        response.setContentType("text/html; charset=gb2312");

        String strLine="";
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        List<String> ids = new ArrayList<>();

        while((strLine = reader.readLine()) != null)
        {
            String[] parts = strLine.split("&");

            ids.add(parts[0].substring(3));
        }

        iClockCommandService.deleteIClockCommandById(ids);

        response.getWriter().print("OK");
    }
}
