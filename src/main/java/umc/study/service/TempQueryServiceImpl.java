package umc.study.service;

import apiPayload.code.status.ErrorStatus;
import apiPayload.exception.handler.TempHandler;

public class TempQueryServiceImpl implements TempQueryService {


    @Override
    public void CheckFlag(Integer flag) {
        if (flag == 1)
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
    }
}
