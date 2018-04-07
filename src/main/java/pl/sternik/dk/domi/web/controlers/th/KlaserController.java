package pl.sternik.dk.domi.web.controlers.th;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import pl.sternik.dk.domi.entities.Znaczek;
import pl.sternik.dk.domi.services.KlaserService;
import pl.sternik.dk.domi.services.NotificationService;


@Controller
public class KlaserController {

    @Autowired
//    @Qualifier("spring-data")
    @Qualifier("tablica")
//    @Qualifier("lista")
    private KlaserService klaserService;

    @Autowired
    private NotificationService notificationService;

//    @ModelAttribute("statusyAll")
//    public List<Status> populateStatusy() {
//        return Arrays.asList(Status.ALL);
//    }

    @ModelAttribute("znaczkiAll")
    public List<Znaczek> populateZnaczki() {
        return this.klaserService.findAll();
    }

    @ModelAttribute("znaczkiToSell")
    public List<Znaczek> populateZnaczkiToSell() {
        return this.klaserService.findAllToSell();
    }

//    @ModelAttribute("coinsLast3")
//    public List<Znaczek> populateLast3Znaczki() {
//        return this.klaserService.findLatest3();
//    }

    @RequestMapping({ "/", "/index" })
    public String index(Model model) {
        return "th/index";
    }

    @RequestMapping(value = "/znaczki", method = RequestMethod.GET)
    public String showMainPage(Model model) {
        model.addAttribute("MyMessages",  notificationService.getNotificationMessages());
        return "th/klaser";
    }

    @RequestMapping("/tosell")
    public String showToSellPage() {
        return "th/tosell";
    }

}