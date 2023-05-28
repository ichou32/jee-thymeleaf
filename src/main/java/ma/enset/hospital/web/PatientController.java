package ma.enset.hospital.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.enset.hospital.entities.Patient;
import ma.enset.hospital.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;
    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name="page", defaultValue = "0") int page,
                        @RequestParam(name="size", defaultValue = "2") int size,
                        @RequestParam(name="keyword", defaultValue = "") String kw){

        Page<Patient> patients = patientRepository.findByNomContains(kw, PageRequest.of(page,size));
        model.addAttribute("patients", patients.getContent());
        model.addAttribute("pages", new int[patients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", kw);
        return "patient";
    }
    @GetMapping("/admin/delete")
//    @PreAuthorize("ADMIN")
    public String delete(long id){
        patientRepository.deleteById(id);
            return "redirect:/index";
    }

    @GetMapping("/admin/formPatient")
//    @PreAuthorize("ADMIN")
    public String formPatient(Model model){
        model.addAttribute("patient", new Patient());
        return "/formPatient";
    }

    @PostMapping("/admin/save")
//    @PreAuthorize("ADMIN")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "") String keyword){
        if(bindingResult.hasErrors()) return "formPatient";
        patientRepository.save(patient);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/editPatient")
//    @PreAuthorize("ADMIN")
    public String editPatient(Model model, long id, String keyword, int page){
        Patient patient =patientRepository.findById(id).orElse(null);
        if(patient==null) throw new RuntimeException("patient introuvable");

        model.addAttribute("patient", patient);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editPatient";
    }
}
