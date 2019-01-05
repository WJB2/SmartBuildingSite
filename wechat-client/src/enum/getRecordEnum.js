const LOST = 'LOST';
const NORAML = 'NORAML';
const ABNORMAL = 'ABNORMAL';

function getRecordText(value){
    if (!value) {
        return '缺卡';
    }

    if (value === 'LOST') {
        return '缺卡';
    }

    if (value === "NORAML"){
        return "正常出勤"
    }

    if (value === "ABNORMAL"){
        return "迟到或早退"
    }
}

export default {
    LOST,
    NORAML,
    ABNORMAL,
    getRecordText,
};