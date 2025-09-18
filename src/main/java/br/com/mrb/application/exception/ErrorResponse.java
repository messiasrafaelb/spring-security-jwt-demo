package br.com.mrb.application.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Getter
@Builder
public class ErrorResponse extends RepresentationModel<ErrorResponse> {
    private Instant timestamp;
    private int status;
    private String error;
    private String message;
    private List<String> messages;
    private Map<String, List<String>> fieldErrors;
}
