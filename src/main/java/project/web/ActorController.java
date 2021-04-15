package project.web;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.annotation.Title;
import project.components.ActorImageValidator;
import project.model.binding.ActorBindingModel;
import project.model.service.ActorServiceModel;
import project.service.ActorService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/actors")
public class ActorController {
    private final ActorService actorService;
    private final ModelMapper modelMapper;
    private final ActorImageValidator actorImageValidator;

    public ActorController(ActorService actorService,
                           ModelMapper modelMapper,
                           ActorImageValidator actorImageValidator) {
        this.actorService = actorService;
        this.modelMapper = modelMapper;
        this.actorImageValidator = actorImageValidator;
    }

    @Title(name = "Add Actor")
    @GetMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MAIN_ADMIN')")
    public String add(Model model) {
        if (!model.containsAttribute("actorBindingModel")) {
            model.addAttribute("actorBindingModel", new ActorBindingModel());
        }
        return "actor/add";
    }

    @Title(name = "Add Author")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MAIN_ADMIN')")
    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("actorBindingModel") ActorBindingModel actorBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) throws IOException {
        this.actorImageValidator.validate(actorBindingModel, bindingResult);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("actorBindingModel", actorBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.actorBindingModel", bindingResult);

            return "redirect:/actors/add";
        }
        this.actorService.addActor(this.modelMapper.map(actorBindingModel, ActorServiceModel.class));

        return "redirect:/movies/add";
    }

    @Title(name = "Actor Details")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MAIN_ADMIN', 'USER')")
    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable("id") String id, ModelAndView modelAndView) {
        modelAndView.addObject("author", this.actorService.findById(id));
        modelAndView.setViewName("actor/details");

        return modelAndView;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MAIN_ADMIN')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:/";
        }
        this.actorService.delete(id);

        return "redirect:/home";
    }
}
