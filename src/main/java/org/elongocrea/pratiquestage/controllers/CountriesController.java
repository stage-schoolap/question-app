package org.elongocrea.pratiquestage.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.elongocrea.pratiquestage.models.Countries;
import org.elongocrea.pratiquestage.services.CountriesService;
import org.elongocrea.pratiquestage.utils.core.AppUtils;
import org.elongocrea.pratiquestage.utils.core.ViewUtils;
import org.elongocrea.pratiquestage.utils.dtos.CountriesDTO;
import org.elongocrea.pratiquestage.utils.mappers.CountriesMapper;
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
@RequestMapping("/countries")
public class CountriesController {

    @Autowired
    private CountriesService service;

    @Autowired
    private CountriesMapper mapper;

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
            final CountriesDTO row = (id != 0) ? mapper.mapToDTO(service.getById(id)) : new CountriesDTO();

            model.addAttribute("myTitle", this.getFormTitle(row, request));
            model.addAttribute("myDto", row);

        } catch (Exception ex) {
            model.addAttribute("myError", ex.getLocalizedMessage());
        }

        return viewUtils.countries_form;

    }

    @GetMapping
    public String get(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                      HttpServletRequest request, Model model) {
        try {
            final List<Countries> data = service.get(keyword);

            model.addAttribute("myData", data);
            model.addAttribute("myKeyword", keyword);
        } catch (Exception ex) {
            model.addAttribute("myError", ex.getLocalizedMessage());
        }

        return viewUtils.countries_view;
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("myDto") CountriesDTO dto, BindingResult result, Model model,
            RedirectAttributes redAttr, HttpServletRequest request) {
        final Locale locale = resolver.resolveLocale(request);

        //Process
        try {
            final long id = dto != null ? dto.getId() : 0;
            service.save(dto, result, locale);
            if (id == 0) { // New
                model.addAttribute("myTitle", this.getFormTitle(new CountriesDTO(), request));
                model.addAttribute("myDto", new CountriesDTO());
                model.addAttribute("myMessage", msgSrc.getMessage("message.taskSuccessfullyCompleted", null, locale));

                return viewUtils.countries_form;

            } else {
                redAttr.addFlashAttribute("myMessage",
                        msgSrc.getMessage("message.taskSuccessfullyCompleted", null, locale));

                return "redirect:/countries";
            }
        } catch (Exception ex) {
            model.addAttribute("myTitle", this.getFormTitle(dto, request));

            result.reject("myError", appUtils.getErrorMsg(ex, locale));

            return viewUtils.countries_form;
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

        return "redirect:/countries";
    }

    // Helper
    private String getFormTitle(CountriesDTO entity, HttpServletRequest request) {
        final Locale locale = resolver.resolveLocale(request);
        final int id = entity != null ? entity.getId() : 0;

        return msgSrc.getMessage("form.countries", null, locale)
                .concat(" / " + (id != 0 ? msgSrc.getMessage("form.edit", null, locale).concat("# " + id)
                        : msgSrc.getMessage("form.new", null, locale)));
    }
}
