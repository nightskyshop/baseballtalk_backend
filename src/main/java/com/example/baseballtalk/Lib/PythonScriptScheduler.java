package com.example.baseballtalk.Lib;

import com.example.baseballtalk.DTO.HitterDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class PythonScriptScheduler {

    @Value("${crawling.path}")
    private String CRAWLING_PATH;

    // 매일 자정에 실행
    @Scheduled(cron = "0 0 0 * * *")
    public void runPythonScript() {
        List<String> file_list = new ArrayList<>();
        file_list.add("/max.py");
        file_list.add("/team.py");
        file_list.add("/player_hitter.py");
        file_list.add("/player_pitcher.py");

        for (String file : file_list) {
            try {
                // Python 스크립트 경로와 실행할 명령어
                String[] command = {"python3", CRAWLING_PATH + file};
                Process process = Runtime.getRuntime().exec(command);

                // 표준 출력 및 에러 출력 읽기
                try (BufferedReader stdOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
                     BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {

                    // 표준 출력에서 "err" 찾기
                    String line;
                    boolean isErrorFound = false;
                    while ((line = stdOutput.readLine()) != null) {
                        System.out.println("Python Output: " + line);
                        if (line.toLowerCase().contains("err")) {
                            isErrorFound = true;
                        }
                    }

                    // 에러 출력에서 "err" 찾기
                    while ((line = stdError.readLine()) != null) {
                        System.err.println("Python Error: " + line);
                        if (line.toLowerCase().contains("err")) {
                            isErrorFound = true;
                        }
                    }

                    // "err" 문자열 발견 시 추가 로깅
                    if (isErrorFound) {
                        System.err.println("The Python script output contains 'err'. Please check the logs for details.");
                    } else {
                        System.out.println("Python script executed successfully without 'err' in the output.");
                    }
                }

                // 프로세스가 종료될 때까지 대기
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}