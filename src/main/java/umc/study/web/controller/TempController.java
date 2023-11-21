package umc.study.web.controller;

import apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.study.converter.TempConvert;
import umc.study.web.dto.TempResponse;

@RestController
@RequestMapping("/temp")
public class TempController {

    @GetMapping("/test")
    public ApiResponse<TempResponse.TempTestDTO> testGet() {
        return ApiResponse.onSuccess(TempConvert.toTempTestDTO());
    }

    @GetMapping("/exception")
    public ApiResponse<TempResponse.TempExceptionDTO> exceptionAPI(@RequestParam Integer flag){

        return null;
    }
}
