
import org.springframework.beans.factory.annotation.Autowired;
import vn.techmaster.jobhunt.repository.EmployerRepository;

@Controller
@RequestMapping("/employer")
public class EmployerController {
    @Autowired
    private EmployerRepository employerRepository;
    @GetMapping
    public String listAllEmployers(Model model) {
        model.addAttribute("employers", employerRepository.getEmployers());
        return "employers";
    }
    
    @GetMapping(value = "/{id")
    public String showEmployerDetail(Model model, @PathVariable String id) {
        model.addAttribute("employers", employerRepository.findById(id));
        return "employer";
    }
}
