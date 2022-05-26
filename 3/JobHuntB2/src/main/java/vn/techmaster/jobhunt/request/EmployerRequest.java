package vn.techmaster.jobhunt.request;

public record EmployerRequest(
                String id,
                String name,
                String website,
                String email,
                String logo_path
                ) {
               // MultipartFile logo) {
}