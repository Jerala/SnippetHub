package edu.snippethub.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValueAlreadyExistException extends Exception {

    private String message;

}
