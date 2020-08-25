package com.swiderski.carrental.soapClient.abstraction;

import com.swiderski.carrental.crud.ApiPageable;
import com.swiderski.carrental.crud.abstraction.AbstractDto;
import com.swiderski.carrental.crud.abstraction.CommonParam;
import com.swiderski.carrental.xlsxGenerator.XlsxGenerator;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

import static com.swiderski.carrental.soapClient.MessageUtils.ID_VALID_MESSAGE;

@Validated
public abstract class AbstractSoapClientController<E extends AbstractDto, T extends CommonSoapClient<E, V>, V extends CommonParam> {

    T clientServiceProxy;

    public AbstractSoapClientController(T clientServiceProxy) {
        this.clientServiceProxy = clientServiceProxy;
    }

    @GetMapping("/{id}")
    public E getById(@PositiveOrZero(message = ID_VALID_MESSAGE) @PathVariable long id) {
        return clientServiceProxy.getById(id);
    }

    @PostMapping
    public E add(@Valid @RequestBody E dto) {
        return clientServiceProxy.add(dto);
    }

    @DeleteMapping("/{id}")
    public E delete(@PositiveOrZero(message = ID_VALID_MESSAGE) @PathVariable long id) {
        return clientServiceProxy.delete(id);
    }

    @PutMapping("/{id}")
    public E update(@Valid @RequestBody E dto,
                    @PositiveOrZero(message = ID_VALID_MESSAGE) @PathVariable long id) {
        return clientServiceProxy.update(dto, id);
    }

    @GetMapping
    @ApiPageable
    public Page<E> getAll(@ModelAttribute V param,
                          @ApiIgnore @NotNull Pageable pageable) {
        return clientServiceProxy.getAll(param, pageable);
    }

    @GetMapping(value = "/excel", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> getExcel(V param) {
        Page<E> all = clientServiceProxy.getAll(param, PageRequest.of(0, 20, Sort.unsorted()));
        byte[] excel = XlsxGenerator.build(all.getContent());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"report" + LocalDateTime.now() + ".xlsx\"")
                .body(excel);
    }

}
