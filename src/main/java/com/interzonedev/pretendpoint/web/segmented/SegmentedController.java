package com.interzonedev.pretendpoint.web.segmented;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.interzonedev.pretendpoint.web.PretendPointController;

@Controller
@RequestMapping(value = "/segmented")
public class SegmentedController extends PretendPointController {

    @ResponseBody
    @RequestMapping(method = { RequestMethod.GET })
    public void writeSegmentedResponse(SegmentedForm delayForm, HttpServletResponse response)
            throws InterruptedException, IOException {

        log.debug("writeSegmentedResponse: Start");

        int segments = Math.max(1, delayForm.getSegments());

        long delay = Math.max(0, delayForm.getDelay());

        log.debug("writeSegmentedResponse: Outputing " + segments + " segments with " + delay + " ms delay before each");

        MediaType contentType = new MediaType("text", "plain", Charset.forName("utf-8"));
        response.setContentType(contentType.toString());

        OutputStream out = response.getOutputStream();

        for (int i = 0; i < segments; i++) {
            Thread.sleep(delay);

            String output = "Segment" + i;

            out.write(output.getBytes());
            out.flush();
        }

        log.debug("writeSegmentedResponse: End");

    }

}
