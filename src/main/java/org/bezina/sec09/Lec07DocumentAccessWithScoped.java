package org.bezina.sec09;

import org.bezina.sec09.controller.DocumentController;
import org.bezina.sec09.security.scopedvalue.AuthenticationService;
import org.bezina.sec09.security.scopedvalue.SecurityContextHolder;
import org.bezina.utils.CommonUtils;

import java.time.Duration;

public class Lec07DocumentAccessWithScoped {
    private static final DocumentController documentController = new DocumentController(SecurityContextHolder::getContext);

    static void main() {

        Thread.ofVirtual().name("admin").start(() -> documentAccessWorkflow(1, "password"));
        Thread.ofVirtual().name("editor").start(() -> documentAccessWorkflow(2, "password"));

        CommonUtils.sleep(Duration.ofSeconds(1));

    }

    private static void documentAccessWorkflow(Integer userId, String password){
        AuthenticationService.loginAndExecute(userId, password, () -> {
            documentController.read();
            documentController.edit();
            documentController.delete();
        });
    }

}
