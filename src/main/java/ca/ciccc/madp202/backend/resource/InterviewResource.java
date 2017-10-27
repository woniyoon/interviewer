package ca.ciccc.madp202.backend.resource;


import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ca.ciccc.madp202.backend.model.CollectedAnswer;
import ca.ciccc.madp202.backend.model.Credential;
import ca.ciccc.madp202.backend.model.HistoryRecord;
import ca.ciccc.madp202.backend.model.InterviewResult;
import ca.ciccc.madp202.backend.model.Profile;
import ca.ciccc.madp202.backend.model.Quiz;
import ca.ciccc.madp202.backend.model.User;
import ca.ciccc.madp202.backend.service.CredentialService;
import ca.ciccc.madp202.backend.service.InterviewResultService;
import ca.ciccc.madp202.backend.service.InterviewService;
import ca.ciccc.madp202.backend.service.ProfileService;

@Path("interview")
public class InterviewResource {
	
	@Path("/users")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Profile postUser(User user) {
		ProfileService profileService = new ProfileService();
		Profile profile = new Profile();
		profileService.addUser(user);
		profileService.addExtraInfo(user, profile);
		profile.setFirstName(user.getFirstName());
		profile.setLastName(user.getLastName());
		profile.setCountry(user.getNationality());
		profile.setUsername(user.getUsername());
		profile.setToken("token");
		return profile;
	}
	
	@Path("/login")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Profile getLogin(Credential credential) {
		CredentialService cs = new CredentialService();
		return cs.getProfileEntity(credential);
	}

	
	@Path("/topics/{interview_topic}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Quiz getInterview(@PathParam("interview_topic")String interview){
		System.out.println(interview);
		InterviewService interviewService = new InterviewService();
		Quiz quiz = new Quiz();
		interviewService.setDetails(interview, quiz);
		interviewService.loadQuestions(quiz.getInterviewID() ,quiz);
		
		return quiz;
	}
	


	
	@Path("/history")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public InterviewResult markAnswers(CollectedAnswer collectedAnswer) {

		InterviewResult interviewResult = new InterviewResult();
		InterviewResultService irs = new InterviewResultService();

		
		interviewResult.setDate(new Date());
		interviewResult.setUserID(collectedAnswer.getUserID());
		interviewResult.setInterviewID(collectedAnswer.getInterviewID());
		irs.marking(interviewResult, collectedAnswer);
		interviewResult.setScore(interviewResult.getCorrect()*10);
		irs.saveHistory(interviewResult);
		return interviewResult;
	}
	
	
	@Path("/history/user/{userID}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public HistoryRecord getHistory(@PathParam("userID")int userID) {
		InterviewResultService irs = new InterviewResultService();
		return irs.getHistory(userID);
	}
	
}
