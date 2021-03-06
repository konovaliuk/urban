package com.grubjack.cinema.web.command;

import com.grubjack.cinema.exception.DaoException;
import com.grubjack.cinema.model.DayOfWeek;
import com.grubjack.cinema.model.TimeOfDay;
import com.grubjack.cinema.service.ServiceFactory;
import com.grubjack.cinema.util.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.grubjack.cinema.util.ConfigManager.*;

/**
 * {@code ShowScheduleCommand} implementation of interface {@code Command}
 */
public class ShowScheduleCommand implements Command {
    /**
     * Class logger
     */
    private static Logger log = LoggerFactory.getLogger(ShowScheduleCommand.class);

    /**
     * Set to sessions attribute:
     * - all names day of week
     * - all names time of day
     * - showService for find show with day and time
     *
     * @param request
     * @param response
     * @return path to schedules page
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        log.info("Executing with session id {}", request.getSession().getId());
        request.getSession().setAttribute(DAYS_ATTR, DayOfWeek.values());
        request.getSession().setAttribute(TIMES_ATTR, TimeOfDay.values());
        request.getSession().setAttribute(SCHEDULE_ATTR, ServiceFactory.getInstance().getShowService().getSchedule());
        return ConfigManager.getInstance().getProperty(MAIN_PAGE_PATH);
    }
}