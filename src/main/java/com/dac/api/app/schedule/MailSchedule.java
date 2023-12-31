package com.dac.api.app.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dac.api.app.model.activity.Activity;
import com.dac.api.app.model.user.User;
import com.dac.api.app.service.activity.ActivityService;
import com.dac.api.app.service.mail.MailService;

@Component
public class MailSchedule {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private MailService mailService;

    @Scheduled(cron = "0 */30 * * * *")
    public void sendMailAlert() {
        List<Activity> activities = this.activityService.getActivitiesStartingWithinNextHour();

        for (Activity activity : activities) {
            if (!activity.isMailSent()) {
                List<User> favoritos = activity.getFavoritedByUsers();

                for (User favorito : favoritos) {
                    String emailSubject = "Lembrete de atividade";
                    String emailText = "Olá {user_name}, voce está recebendo esse email pois favoritou a atividade ({activity_name}) que começará às {activity_startTime}. Te esperamos no {activity_location}";

                    emailSubject = emailSubject.replace("{activity_name}", activity.getName());
                    emailText = emailText.replace("{activity_name}", activity.getName());
                    emailText = emailText.replace("{activity_startTime}", activity.getStartTime().toString());
                    emailText = emailText.replace("{activity_location}", activity.getSpace().getLocation());
                    emailText = emailText.replace("{user_name}", favorito.getUsername());

                    this.mailService.sendEmail(favorito.getEmail(), emailSubject, emailText);
                }

                activity.setMailSent(true);
                this.activityService.setMailSent(activity);
            }
        }
    }
}
