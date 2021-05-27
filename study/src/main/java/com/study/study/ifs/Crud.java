package com.study.study.ifs;

import com.study.study.model.header.Header;

public interface Crud<Res, Req> {
    Header<Res> create(Header<Req> request);
    Header<Res> update(Header<Req> request);
    Header<Res> read(Long id);
    Header delete(Long id);
}
