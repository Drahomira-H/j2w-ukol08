package cz.czechitas.java2webapps.ukol8.controller;

import cz.czechitas.java2webapps.ukol8.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for displaying posts
 */
@Controller
public class PostController {
    private final PostService service;

    @Autowired
    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping("/")
//    public ModelAndView list(@PageableDefault(sort = {"published"}) Pageable pageable) {
//        return new ModelAndView("index")
//                .addObject("posts", service.list(pageable));
//    }

    public ModelAndView list(@PageableDefault Pageable pageable) {
        return new ModelAndView("index")
                .addObject("posts", service.listByPublished());
    }

    @GetMapping("/post/{slug}")
    public ModelAndView postDetail(@PathVariable String slug) {
        return new ModelAndView("post-detail")
                .addObject("post", service.singlePost(slug));
    }

    /**
     * Získání aktuální URL s query parametry bez parametrů {@code size} a {@code page}.
     */
    @ModelAttribute("currentURL")
    public String currentURL(HttpServletRequest request) {
        UrlPathHelper helper = new UrlPathHelper();
        return UriComponentsBuilder
                .newInstance()
                .path(helper.getOriginatingRequestUri(request))
                .query(helper.getOriginatingQueryString(request))
                .replaceQueryParam("size")
                .replaceQueryParam("page")
                .build()
                .toString();
    }
}
