package com.sinam7.bulletinboard.domain.article;

import com.sinam7.bulletinboard.domain.member.Member;
import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Data()
public class Article {
    private Long id;
    private String title;
    private String content;
    private Member writer;
    private Timestamp timestamp;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public String getWriterName() {
        return writer.getName();
    }


    public String getDateTimeFormat() {
        if (timestamp == null) return "null";

        return dateFormat.format(timestamp);
    }


}
