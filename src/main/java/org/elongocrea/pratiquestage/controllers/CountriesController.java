package org.elongocrea.pratiquestage.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.elongocrea.pratiquestage.models.Countries;
import org.elongocrea.pratiquestage.models.Users;
import org.elongocrea.pratiquestage.services.CountriesService;
import org.elongocrea.pratiquestage.services.UsersService;
import org.elongocrea.pratiquestage.utils.core.AppUtils;
import org.elongocrea.pratiquestage.utils.core.ViewUtils;
import org.elongocrea.pratiquestage.utils.dtos.CountriesDTO;
import org.elongocrea.pratiquestage.utils.dtos.UsersDTO;
import org.elongocrea.pratiquestage.utils.mappers.CountriesMapper;
import org.elongocrea.pratiquestage.utils.mappers.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountriesController {

    private final CountriesService service;
    private final CountriesMapper mapper;
    private final LocaleResolver resolver;
    private final MessageSource msgSrc;
    private final ViewUtils viewUtils;

    //
    @GetMapping("/get/form/{id}")
    public String getForm(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        CountriesDTO row = id != 0 ? mapper.mapToDTO(service.getById(id)) : new CountriesDTO();
        model.addAttribute("myTitle", getFormTitle(row, request));
        model.addAttribute("myDto", row);
        return viewUtils.countries_form;
    }

    @GetMapping
    public String index(
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            @RequestParam(value = "nameFr", required = false, defaultValue = "") String nameFr,
            HttpServletRequest request, Model model
    ) {
        List<Countries> data = service.get(nameFr, keyword);
        model.addAttribute("myData", data);
        model.addAttribute("myKeyword", keyword);

        return viewUtils.countries_view;
    }

    @PostMapping
    public String save(
            @Validated @ModelAttribute("myDto") CountriesDTO dto,
            BindingResult result,
            Model model,
            RedirectAttributes redAttr,
            HttpServletRequest request
    ) {
        Locale locale = resolver.resolveLocale(request);

        //Process
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
        return "redirect:/countries";
    }

    // Helper methods
    private String getFormTitle(CountriesDTO entity, HttpServletRequest request) {
        Locale locale = resolver.resolveLocale(request);
        int id = entity != null ? entity.getId() : 0;

        return msgSrc.getMessage("form.countries", null, locale)
                .concat(" / " + (id != 0 ? msgSrc.getMessage("form.edit", null, locale).concat("# " + id)
                        : msgSrc.getMessage("form.new", null, locale)));
    }

    private String handleSaveSuccess(Model model, RedirectAttributes redAttr, HttpServletRequest request, Locale locale, long id) {
        if (id == 0) {
            model.addAttribute("myTitle", getFormTitle(new CountriesDTO(), request));
            model.addAttribute("myDto", new CountriesDTO());
            model.addAttribute("myMessage", msgSrc.getMessage("message.taskSuccessfullyCompleted", null, locale));
            return viewUtils.countries_form;
        } else {
            redAttr.addFlashAttribute("myMessage", msgSrc.getMessage("message.taskSuccessfullyCompleted", null, locale));
            return "redirect:/countries";
        }
    }

    private String handleSaveError(CountriesDTO dto, BindingResult result, Model model, HttpServletRequest request, Exception ex, Locale locale) {
        model.addAttribute("myTitle", getFormTitle(dto, request));
        result.reject("myError", ex.getLocalizedMessage());
        return viewUtils.countries_form;
    }
}
