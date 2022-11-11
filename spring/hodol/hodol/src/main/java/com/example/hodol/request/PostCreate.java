package com.example.hodol.request;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString()
@NoArgsConstructor
@Setter
public class PostCreate {
    public String title;
    public String content;
}
