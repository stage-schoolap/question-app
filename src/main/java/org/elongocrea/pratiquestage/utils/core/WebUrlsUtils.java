package org.elongocrea.pratiquestage.utils.core;

import org.springframework.stereotype.Component;

@Component(value = "webUrls")
public class WebUrlsUtils {

    public final String home = "/";

    public final String login = "/login";
    public final String login_post = "/login/post";
    public final String logout = "/logout";

    public final String users_view = "/users";
    public final String users_form = "/users/get/form/{id}";
    public final String users_save = "/users";
    public final String users_delete = "/users/delete/{id}";

    public final String people_view = "/people";
    public final String people_form = "/people/get/form/{id}";
    public final String people_save = "/people";
    public final String people_delete = "/people/delete/{id}";

    public final String countries_view = "/countries";
    public final String countries_form = "/countries/get/form/{id}";
    public final String countries_save = "/countries";
    public final String countries_delete = "/countries/delete/{id}";

    public final String cities_view = "/cities";
    public final String cities_form = "/cities/get/form/{id}";
    public final String cities_save = "/cities";
    public final String cities_delete = "/cities/delete/{id}";

    public final String person_types_view = "/person_types";
    public final String person_types_form = "/person_types/get/form/{id}";
    public final String person_types_save = "/person_types";
    public final String person_types_delete = "/person_types/delete/{id}";
}
