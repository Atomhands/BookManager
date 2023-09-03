package com.niehao.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JSONUtil {

    public static void writerJson(HttpServletResponse resp, Object data) {
        PrintWriter writer = null;
        try {
            resp.setCharacterEncoding("UTF-8");
            writer = resp.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(writer, data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

}
