package org.elongocrea.pratiquestage.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.elongocrea.pratiquestage.models.Users;
import org.elongocrea.pratiquestage.services.UsersService;
import org.elongocrea.pratiquestage.utils.core.ViewUtils;
import org.elongocrea.pratiquestage.utils.dtos.UsersDTO;
import org.elongocrea.pratiquestage.utils.mappers.UsersMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService service;
    private final UsersMapper mapper;
    private final LocaleResolver resolver;
    private final MessageSource msgSrc;
    private final ViewUtils viewUtils;

    @GetMapping("/get/form/{id}")
    public String getForm(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        UsersDTO row = id != 0 ? mapper.mapToDTO(service.getById(id)) : new UsersDTO();
        model.addAttribute("myTitle", getFormTitle(row, request));
        model.addAttribute("myDto", row);
        return viewUtils.users_form;
    }

    @GetMapping
    public String index(
        @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
        @RequestParam(value = "active", required = false, defaultValue = "false") boolean active,
        @RequestParam(value = "block", required = false, defaultValue = "false") boolean block,
        @RequestParam(value = "connected", required = false, defaultValue = "false") boolean connected,
        HttpServletRequest request, Model model
    ) {
        List<Users> data = service.get(active, block, connected, keyword);
        model.addAttribute("myData", data);
        model.addAttribute("myCheck", active);
        model.addAttribute("blocked", block);
        model.addAttribute("myKeyword", keyword);
        return viewUtils.users_view;
    }

    @PostMapping
    public String save(
            @Validated @ModelAttribute("myDto") UsersDTO dto,
            BindingResult result,
            Model model,
            RedirectAttributes redAttr,
            HttpServletRequest request
    ) {
        Locale locale = resolver.resolveLocale(request);
        try {
            long id = dto != null ? dto.getId() : 0;
            service.save(dto, result, locale);
            return handleSaveSuccess(model, redAttr, request, locale, id);
        } catch (Exception ex) {
            return handleSaveError(dto, result, model, request, ex, locale);
        }
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        try {
            service.delete(id, resolver.resolveLocale(request));
        } catch (Exception ex) {
            model.addAttribute("myError", ex.getLocalizedMessage());
        }
        return "redirect:/users";
    }

    // Helper methods
    private String getFormTitle(UsersDTO entity, HttpServletRequest request) {
        Locale locale = resolver.resolveLocale(request);
        int id = entity != null ? entity.getId() : 0;
        return msgSrc.getMessage("form.users", null, locale)
                .concat(" / " + (id != 0 ? msgSrc.getMessage("form.edit", null, locale).concat("# " + id)
                        : msgSrc.getMessage("form.new", null, locale)));
    }

    private String handleSaveSuccess(Model model, RedirectAttributes redAttr, HttpServletRequest request, Locale locale, long id) {
        if (id == 0) {
            model.addAttribute("myTitle", getFormTitle(new UsersDTO(), request));
            model.addAttribute("myDto", new UsersDTO());
            model.addAttribute("myMessage", msgSrc.getMessage("message.taskSuccessfullyCompleted", null, locale));
            return viewUtils.users_form;
        } else {
            redAttr.addFlashAttribute("myMessage", msgSrc.getMessage("message.taskSuccessfullyCompleted", null, locale));
            return "redirect:/users";
        }
    }

    private String handleSaveError(UsersDTO dto, BindingResult result, Model model, HttpServletRequest request, Exception ex, Locale locale) {
        model.addAttribute("myTitle", getFormTitle(dto, request));
        result.reject("myError", ex.getLocalizedMessage());
        return viewUtils.users_form;
    }
}
