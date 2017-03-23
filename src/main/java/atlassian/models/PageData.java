package atlassian.models;

import lombok.Data;

/**
 * Created by rajeshkalloor on 9/24/16.
 *
 * The PageData class is used to hold the information of the pages
 * created like say - title, body, restrictions, pageLink, parent etc
 *
 */
@Data
public class PageData {

    private String title;
    private String body;
    private Access restrictions;
    private String pageLink;
}
