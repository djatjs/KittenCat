package org.zerock.b01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.zerock.b01.domain.Place;
import org.zerock.b01.dto.*;
import org.zerock.b01.repository.PlaceRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.zerock.b01.domain.QPlace.place;


@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class PlaceServiceImpl implements PlaceService{

    private final ModelMapper modelMapper;

    private final PlaceRepository placeRepository;

    @Override
    public PlaceDTO readOne(Integer p_ord) {

        Optional<Place> result = placeRepository.findById(p_ord);

        Place place = result.orElseThrow();

        PlaceDTO placeDTO = entityToDTO(place);

        return placeDTO;
    }
    @Override
    public PageResponseDTO<PlaceDTO> list(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable("p_ord");

        Page<Place> result = placeRepository.searchAll(pageable);

        List<PlaceDTO> dtoList = result.getContent().stream()
                .map(place -> modelMapper.map(place,PlaceDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<PlaceDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();

    }
}
