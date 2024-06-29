package org.elongocrea.pratiquestage.utils.core;

import org.apache.commons.lang3.StringUtils;
import org.elongocrea.pratiquestage.utils.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Component
public class AppUtils {

    @Autowired
    private MessageSource msgSrc;

    public String getFilterKeyword(String keyword) {
        return StringUtils.isEmpty(keyword) ? "%%" : "%" + keyword + "%";
    }

    public <T> T convertToObject(List<T> data) {
        return !CollectionUtils.isEmpty(data) ? data.get(0) : null;
    }

    public java.sql.Timestamp getSQLTimestamp(LocalDateTime dateTime) {
        return dateTime != null ? java.sql.Timestamp.valueOf(dateTime) : null;
    }

    public LocalDateTime getLocalDateTime(java.sql.Timestamp datetime) {
        return datetime != null ? datetime.toLocalDateTime() : null;
    }

    public void validate(BindingResult result) {
        if (result.hasErrors()) {
            final StringJoiner joiner = new StringJoiner(", ");

            final List<ObjectError> errors = result.getAllErrors();
            if (!CollectionUtils.isEmpty(errors)) {
                final List<String> emsgs = errors.stream().map(e -> e.getDefaultMessage())
                        .distinct()
                        .collect(Collectors.toList());

                for (String item : emsgs) {
                    joiner.add(item);
                }
            }

            throw new MyInvalidEntryException(String.valueOf(joiner));

        }
    }

    public String getErrorMsg(Exception ex, Locale locale) {
        final String error;

        if (ex instanceof MyNotFoundEntryException) {
            error = msgSrc.getMessage("error.noDataFound", null, locale);

        } else if (ex instanceof MyInvalidEntryException) {
            error = msgSrc.getMessage("error.invalidEntry", null, locale);

        } else if (ex instanceof MyDuplicatedEntryException) {
            error = msgSrc.getMessage("error.duplicatedEntry1", null, locale);

        } else if (ex instanceof MyAccessDeniedException) {
            error = msgSrc.getMessage("error.accessDenied", null, locale);

        } else if (ex instanceof MyInvalidCredsException) {
            error = msgSrc.getMessage("error.invalidCredentials", null, locale);

        } else {
            error = msgSrc.getMessage("error.system", null, locale);
        }

        final String error_details = ex.getLocalizedMessage();

        return error.concat(StringUtils.isNotEmpty(error_details) ? ": " + error_details : ". ");
    }
}
