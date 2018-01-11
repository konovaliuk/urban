package com.grubjack.cinema.web.command;

import com.grubjack.cinema.exception.DaoException;
import com.grubjack.cinema.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.grubjack.cinema.util.ConfigManager.TICKET_ID_PARAM;

/**
 * Created by Urban Aleksandr
 */
public class CancelTicketCommand implements Command {
    private static Logger log = LoggerFactory.getLogger(DeleteUserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        log.info("Executing with session id {}", request.getSession().getId());
        String ticketId = request.getParameter(TICKET_ID_PARAM);
        if (ticketId != null && !ticketId.isEmpty()) {
            log.info("Delete ticket with id {}", Integer.parseInt(ticketId));
            ServiceFactory.getInstance().getTicketService().cancel(Integer.parseInt(ticketId));
        }
        return new ShowTicketsCommand().execute(request, response);
    }
}