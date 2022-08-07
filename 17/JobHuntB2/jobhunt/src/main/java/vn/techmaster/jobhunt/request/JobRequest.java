package vn.techmaster.jobhunt.request;

import javax.validation.constraints.NotBlank;

import vn.techmaster.jobhunt.model.City;
import vn.techmaster.jobhunt.model.Employer;

/**
 * private String id;
 * private String emp_id;
 * private String title;
 * private String description;
 * private City city;
 */

public record JobRequest(
    String id,
    Employer employer,
        @NotBlank(message = "title cannot null") String title,
        @NotBlank(message = "description cannot null") String description,
    String city) {
}