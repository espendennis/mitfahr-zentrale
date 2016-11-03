package com.espen.ws.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.espen.ws.model.Offer;
import com.espen.ws.model.User;
import com.espen.ws.services.OffersServiceInterface;
import com.espen.ws.services.UsersServiceInterface;

@Controller
public class HomeController {

	@Autowired
	private OffersServiceInterface offersService;

	@Autowired
	private UsersServiceInterface usersService;

	@RequestMapping(value = "/")
	public String showHome(Model model) {
		Collection<Offer> offers = offersService.findAll();
		model.addAttribute("offers", offers);
		return "home";
	}

	@RequestMapping(value = "/createuser")
	public String showCreateUser() {
		return "createuser";
	}

	@RequestMapping(value = "/createoffer")
	public String showCreateOffer(Model model, Principal principal) {
		model.addAttribute("username", principal.getName());
		return "createoffer";
	}

	@RequestMapping(value = "/login")
	public String showLogin() {
		return "login";
	}

	@RequestMapping(value = "/about")
	public String showAbout() {
		return "about";
	}

	@RequestMapping(value = "/api")
	public String showAPI() {
		return "api";
	}

	@RequestMapping(value = "/ownprofile")
	public String showOwnProfile(Principal principal) {
		return "redirect:/profile/" + principal.getName();
	}

	@RequestMapping(value = "/profile/{username}")
	public String showProfile(@PathVariable String username, Model model) {
		User user = usersService.findOne(username);
		model.addAttribute("user", user);
		return "profile";
	}

	@RequestMapping(value = "offer/{id}")
	public String showOffer(@PathVariable int id, Model model) {
		Offer offer = offersService.findOneById(id);
		User user = usersService.findOne(offer.getUsername());
		model.addAttribute("offer", offer);
		model.addAttribute("user", user);

		return "offer";
	}

	@RequestMapping(value = "/newuser")
	public String creatUser(User user) {
		usersService.save(user);
		return "redirect:/usercreated";
	}

	@RequestMapping(value = "/usercreated")
	public String userCreated() {
		return "usercreated";
	}

	@RequestMapping(value = "/newoffer")
	public String createOffer(Offer offer, Principal principal) {
		offer.setUsername(principal.getName());
		offersService.save(offer);
		return "redirect:/offercreated";
	}

	@RequestMapping(value = "/offercreated")
	public String offerCreated() {
		return "offercreated";
	}
}
