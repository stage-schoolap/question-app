package org.elongocrea.pratiquestage.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.elongocrea.pratiquestage.models.Users;
import org.elongocrea.pratiquestage.services.UsersService;
import org.elongocrea.pratiquestage.utils.core.AppUtils;
import org.elongocrea.pratiquestage.utils.core.ViewUtils;
import org.elongocrea.pratiquestage.utils.dtos.UsersDTO;
import org.elongocrea.pratiquestage.utils.mappers.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService service;

    @Autowired
    private UsersMapper mapper;

    @Autowired
    private LocaleResolver resolver;

    @Autowired
    private MessageSource msgSrc;

    @Autowired
    private ViewUtils viewUtils;

    @Autowired
    private AppUtils appUtils;

    //
    @GetMapping("/get/form/{id}")
    public String getForm(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        try {
            final UsersDTO row = (id != 0) ? mapper.mapToDTO(service.getById(id)) : new UsersDTO();

            model.addAttribute("myTitle", this.getFormTitle(row, request));
            model.addAttribute("myDto", row);

        } catch (Exception ex) {
            model.addAttribute("myError", ex.getLocalizedMessage());
        }

        return viewUtils.users_form;
    }

    @GetMapping
    public String getRecords(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                             @RequestParam(value = "active", required = false, defaultValue = "false") boolean active,
                             @RequestParam(value = "block", required = false, defaultValue = "false") boolean block,
                             @RequestParam(value = "connected", required = false, defaultValue = "false") boolean connected,
                             HttpServletRequest request, Model model) {
        try {
            if (request.getParameter("active") == null) { // Handle Unchecked checkbox not submitted
                active = false;
            }
            if (request.getParameter("block") == null) { // Handle Unchecked icon not submitted
                block = false;
            }

            final List<Users> data = service.get(active, block, connected, keyword);

            model.addAttribute("myData", data);
            model.addAttribute("myCheck", active);
            model.addAttribute("blocked", block);
            model.addAttribute("myKeyword", keyword);

        } catch (Exception ex) {
            model.addAttribute("myError", ex.getLocalizedMessage());
        }

        return viewUtils.users_view;

    }

    @PostMapping
    public String save(@Valid @ModelAttribute("myDto") UsersDTO dto, BindingResult result, Model model,
                       RedirectAttributes redAttr, HttpServletRequest request) {
        final Locale locale = resolver.resolveLocale(request);

        //Process
        try {
            final long id = dto != null ? dto.getId() : 0;
            service.save(dto, result, locale);
            if (id == 0) {
                model.addAttribute("myTitle", this.getFormTitle(new UsersDTO(), request));
                model.addAttribute("myDto", new UsersDTO());
                model.addAttribute("myMessage", msgSrc.getMessage("message.taskSuccessfullyCompleted", null, locale));

                return viewUtils.users_form;

            } else {
                redAttr.addFlashAttribute("myMessage",
                        msgSrc.getMessage("message.taskSuccessfullyCompleted", null, locale));

                return "redirect:/users?is_active=true";
            }

        } catch (Exception ex) {
            model.addAttribute("myTitle", this.getFormTitle(dto, request));

            result.reject("myError", appUtils.getErrorMsg(ex, locale));

            return viewUtils.users_form;
        }
    }


    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        final Locale locale = resolver.resolveLocale(request);
        try {
            service.delete(id, locale);

        } catch (Exception ex) {
            model.addAttribute("myError", ex.getLocalizedMessage());
        }

        return "redirect:/users";
    }

    // Helper
    private String getFormTitle(UsersDTO entity, HttpServletRequest request) {
        final Locale locale = resolver.resolveLocale(request);
        final int id = entity != null ? entity.getId() : 0;

        return msgSrc.getMessage("form.users", null, locale)
                .concat(" / " + (id != 0 ? msgSrc.getMessage("form.edit", null, locale).concat("# " +  id)
                        : msgSrc.getMessage("form.new", null, locale)));
    }
}
