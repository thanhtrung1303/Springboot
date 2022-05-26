package vn.techmaster.jobhunt.request;

import vn.techmaster.jobhunt.model.City;

/**
 * private String id;
 * private String emp_id;
 * private String title;
 * private String description;
 * private City city;
 */

public record JobRequest(
    String id,
    String emp_id,
    String title,
    String description,
    City city) {
}