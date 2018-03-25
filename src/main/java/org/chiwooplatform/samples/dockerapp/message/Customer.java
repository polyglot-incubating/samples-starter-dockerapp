package org.chiwooplatform.samples.dockerapp.message;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

    @NotNull(message = "ID can not null.")
    private Integer id;

    @NotEmpty(message = "Firstname can not empty.")
    private String firstname;

    @NotEmpty(message = "Lastname can not empty.")
    private String lastname;

    private Double balance;
}