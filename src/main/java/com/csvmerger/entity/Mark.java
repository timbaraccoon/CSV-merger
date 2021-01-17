package com.csvmerger.entity;

import lombok.*;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Mark {

    private final String name;
    private final int quantity;

}
