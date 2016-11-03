package com.espen.documentation;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import com.espen.ws.model.Offer;
import com.espen.ws.model.User;
import com.espen.ws.services.OffersServiceInterface;
import com.espen.ws.services.UsersServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
@TestPropertySource("test.properties")
public class OfferApiDocumentation {

	@Autowired
	private OffersServiceInterface offersService;
	@Autowired
	private UsersServiceInterface usersService;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private RestDocumentationResultHandler document;

	ConstrainedFields fields = new ConstrainedFields(Offer.class);

	@Rule
	public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

	private MockMvc mockMvc;

	Offer offer1 = new Offer("Berlin", "München", "admin", "2016-10-10T08:10", 30);
	Offer offer2 = new Offer("Frankfurt", "Hamburg", "admin", "2016-10-10T08:20", 30);
	User user1 = new User("admin", "Admin", "TestUser", "123456seven", "dennis.espen@comsysto.com", "5551234");

	FieldDescriptor[] offer = new FieldDescriptor[] { fields.withPath("id").description("identifier"),
			fields.withPath("destination").description("The trips' destination"),
			fields.withPath("startingPoint").description("The trips' startingPoint"),
			fields.withPath("username").description("The user who has created the offer"),
			fields.withPath("date").description("The trip's starting time formatted"),
			fields.withPath("price").description("The price per person"),
			fields.withPath("dateObject").description("The trip's starting time as milliseconds") };

	@Before
	public void init() {
		user1.setAuthority("ROLE_ADMIN");
		usersService.save(user1);
		offersService.save(offer1);
		offersService.save(offer2);

		this.document = document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()));

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(documentationConfiguration(this.restDocumentation)).alwaysDo(this.document).build();
	}

	@Test
	public void getOffers() throws Exception {
		mockMvc.perform(get("/api/offers")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(this.document.document(responseFields(fieldWithPath("[]").description("An array of offers"))
						.andWithPrefix("[].", offer)));
	}

	@Test
	public void getOfferById() throws Exception {
		ArrayList<Offer> offers = (ArrayList<Offer>) offersService.findAll();
		Offer offerToTest = offers.get(0);
		mockMvc.perform(get("/api/offers/{id}", offerToTest.getId()))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andDo(this.document.document(responseFields(offer))).andDo(this.document.document(
						pathParameters(parameterWithName("id").description("The id of the requested offer"))));
	}

	@Test
	public void postOffer() throws Exception {
		Offer newOffer = new Offer("Innsbruck", "Lindau", "admin", "2016-10-10T09:50", 40);
		String json = mapper.writeValueAsString(newOffer);
		mockMvc.perform(post("/api/offers").contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
				.andExpect(status().isCreated()).andDo(this.document.document(requestFields(offer)))
				.andDo(this.document.document(responseFields(offer)));
	}

	@Test
	public void putOffer() throws Exception {
		ArrayList<Offer> offers = (ArrayList<Offer>) offersService.findAll();
		Offer offerToChange = offers.get(1);
		offerToChange.setStartingPoint("München");
		offerToChange.setDestination("Frankfurt");
		offerToChange.setPrice(100);
		offerToChange.setUsername("admin");
		offerToChange.setDate("2017-11-11T09:25");
		String json = mapper.writeValueAsString(offerToChange);
		mockMvc.perform(put("/api/offers/{id}", offerToChange.getId()).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(json)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(this.document
						.document(pathParameters(parameterWithName("id").description("The id of the offer to update"))))
				.andDo(this.document.document(requestFields(offer)))
				.andDo(this.document.document(responseFields(offer))).andReturn();

	}

	@Test
	public void deleteOffer() throws Exception {
		ArrayList<Offer> offers = (ArrayList<Offer>) offersService.findAll();
		Offer offerToDelete = offers.get(0);
		mockMvc.perform(delete("/api/offers/{id}", offerToDelete.getId())).andExpect(status().isNoContent())
				.andDo(this.document.document(
						pathParameters(parameterWithName("id").description("The id of the offer to delete"))));
	}

	@Test
	public void getOffersByUsername() throws Exception {
		mockMvc.perform(get("/api/offers/username/{username}", user1.getUsername()))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andDo(this.document.document(pathParameters(
						parameterWithName("username").description("The user whos offers should be retrieved"))))
				.andDo(this.document.document(responseFields(fieldWithPath("[]").description("An array of offers"))
						.andWithPrefix("[].", offer)));
	}

	private static class ConstrainedFields {

		private final ConstraintDescriptions constraintDescriptions;

		ConstrainedFields(Class<?> input) {
			this.constraintDescriptions = new ConstraintDescriptions(input);
		}

		private FieldDescriptor withPath(String path) {
			return fieldWithPath(path).attributes(key("constraints").value(StringUtils
					.collectionToDelimitedString(this.constraintDescriptions.descriptionsForProperty(path), ". ")));
		}
	}
}