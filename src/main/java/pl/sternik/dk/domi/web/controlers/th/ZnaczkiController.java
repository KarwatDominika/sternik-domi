package pl.sternik.dk.domi.web.controlers.th;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import pl.sternik.dk.domi.entities.Status;
import pl.sternik.dk.domi.entities.Znaczek;
import pl.sternik.dk.domi.services.KlaserService;
import pl.sternik.dk.domi.services.NotificationService;
import pl.sternik.dk.domi.services.NotificationService.NotificationMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class ZnaczkiController {

   @Autowired
   private Logger logger;


    @Autowired
    // @Qualifier("spring-data")
    @Qualifier("tablica")
    // @Qualifier("lista")
    private KlaserService klaserService;

    @Autowired
    private NotificationService notifyService;

    @ModelAttribute("statusyAll")
    public List<Status> populateStatusy() {
        return Arrays.asList(Status.ALL);
    }

    @ModelAttribute("MyMessages")
    public List<NotificationMessage> populateMessages() {
        //System.out.println("<3");
        logger.info("<3");
        return notifyService.getNotificationMessages();
    }


    @GetMapping(value = "/znaczki/{id}")
    public String view(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Znaczek> result;
        result = klaserService.findById(id);
        if (result.isPresent()) {
            Znaczek znaczek = result.get();
            model.addAttribute("znaczek", znaczek);
            return "th/znaczek";
        } else {
            notifyService.addErrorMessage("Cannot find znaczek #" + id);
            model.clear();
            return "redirect:/znaczki";
        }
    }

    @RequestMapping(value = "/znaczki/{id}/json", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Znaczek> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Znaczek> result;
        result = klaserService.findById(id);
        if (result.isPresent()) {
            Znaczek znaczek = result.get();
            return new ResponseEntity<Znaczek>(znaczek, HttpStatus.OK);
        } else {
            notifyService.addErrorMessage("Cannot find znaczek #" + id);
            model.clear();
            return new ResponseEntity<Znaczek>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/znaczki", params = { "save" }, method = RequestMethod.POST)
    public String saveZnaczek(@Valid Znaczek znaczek, BindingResult bindingResult, ModelMap model) {
        // @Valid
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            model.addAttribute("MyMessages",  notifyService.getNotificationMessages());
            return "th/znaczek";
        }

        if (znaczek.getStatus() == Status.NOWY) {
           znaczek.setStatus(Status.STARY);
        }

        Optional<Znaczek> result = klaserService.edit(znaczek);
        if (result.isPresent())
            notifyService.addInfoMessage("Zapis udany");
        else
            notifyService.addErrorMessage("Zapis NIE udany");
        model.clear();
        return "redirect:/znaczki";
    }

    @RequestMapping(value = "/znaczki", params = { "create" }, method = RequestMethod.POST)
    public String createZnaczek(@Valid Znaczek znaczek, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            model.addAttribute("MyMessages",  notifyService.getNotificationMessages());
            return "th/znaczek";
        }
        klaserService.create(znaczek);
        model.clear();
        notifyService.addInfoMessage("Zapis nowego znaczka udany");
        return "redirect:/znaczki";
    }

    @RequestMapping(value = "/znaczki", params = { "remove" }, method = RequestMethod.POST)
    public String removeRow(final Znaczek znaczek, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("remove"));
        Optional<Boolean> result = klaserService.deleteById(rowId.longValue());
        //Optional<Boolean> result = klaserService.deleteById(remove.longValue());
        return "redirect:/znaczki";
    }

    @RequestMapping(value = "/znaczki/create", method = RequestMethod.GET)
    public String showMainPages(final Znaczek znaczek) {
        // Ustawiamy date nowego znaczka, na dole strony do dodania
        znaczek.setRokProdukcji(Calendar.getInstance().getTime());
        return "th/znaczek";
    }
}