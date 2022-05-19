# 1. Trong quá trình tạo dự án Spring Boot chúng ta phải khai báo những tham số sau đây : groupID, artifactID. Ý nghĩa các tham số này là gì?

- groupID: thường đặt theo tên của tổ chức/công ty/nhóm tạo ra dự án
- artifactID: Tên của Package dự án, thường lấy theo tên viết tắt của dự án.

# 2. Tại sao phải đảo ngược tên miền trong <groupId>vn.techmaster</groupId>?

package đi từ lớn đến nhỏ, để phân tách các package dễ hơn.

# 3. SpringBoot có 2 cơ chế để quản lý thư viện. Hãy kể tên chúng?

- Maven
- Gradle

# 4. File pom.xml có tác dụng gì?

Tệp pom.xml chứa thông tin về dự án và thông tin cấu hình để maven xây dựng dự án, chẳng hạn như: dependencies, build directory, source directory, test source directory, plugin, goals..

# 5. Trong file pom.xml có các thẻ dependency. Ý nghĩa của chúng là gì?

Các thẻ dependency là các thư viện mở rộng mà người dùng thêm vào để sử dụng cho mục đích của dự án.

# 6. Ý nghĩa của @Controller là gì?

@Controller là một annotation ở class, thường được sử dụng cho Spring Controller truyền thống hay được sử dụng trong các phiên bản Spring từ 4.0 trở xuống. @Controller sẽ được sử dụng với annotation @RequestMapping trên các phương thức để xử lý các request.

# 7. Ý nghĩa của @RequestMapping là gì? Nó có những tham số gì ngoài value?

@RequestMapping: nó được sử dụng để map (ánh xạ) các request.
Nó có nhiều phần tử tùy chọn như consumes, header, method, name, params, path, produces, và value.
Chúng ta sử dụng nó với lớp cũng như phương thức.

# 8. Ý nghĩa của @RequestBody khi đặt trong hàm hứng request để làm gì?

@RequestBody: Nó được sử dụng để liên kết yêu cầu HTTP với một đối tượng trong một tham số của phương thức. Bên trong nó sử dụng HTTP MessageConverters để chuyển đổi phần thân của yêu cầu. Khi chú thích một tham số của phương thức với @RequestBody, Spring sẽ liên kết phần body yêu cầu HTTP đến với tham số đó.

# 9. Hãy trả lời khi nào thì dùng @PathVariable và khi nào nên dùng @RequestParam

- @RequestParam được dùng khi muốn trích xuất dữ liệu từ request query
- @PathVariable được dùng khi muốn trích xuất dữ liệu từ URL path.

# 10. Thứ tự các thành phần đường dẫn @PathVariable có thể hoán đổi được không?

Không.

# 11. @GetMapping khác gì so với @PostMapping?

- @GetMapping: mục đích để lấy thông tin dữ liệu từ server bởi 1 URI đã cung cấp.
- @PostMapping: mục đích để gửi dữ liệu tới server giúp bạn thêm mới dữ liệu vào database.

# 12. Trong các annotation @RequestMapping, @GetMapping, @PostMapping… có tham số produces = MediaType.XXXX ý nghĩa tham số này là gì?

Tham số này định dang kiểu dữ liệu trả về.

# 13. Giải thích ý nghĩa của @RequestBody trong đoạn code dưới đây:

```java
@PostMapping(value = "/message", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public Message echoMessage(@RequestBody Message message){
return message;
}
```

@RequestBody sẽ chuyển Spring convert json data thành đối tượng message

# 14. Cổng mặc định ứng dụng SpringBoot là 8080. Hãy google cách để thay đổi cổng lắng nghe mặc định

tìm application.properties và thêm server.port=8081
