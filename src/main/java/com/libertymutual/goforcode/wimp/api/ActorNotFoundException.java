package com.libertymutual.goforcode.wimp.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such actor") 
public class ActorNotFoundException extends Exception {

		private static final long serialVersionUID = 1654138248009564021L;	

}
