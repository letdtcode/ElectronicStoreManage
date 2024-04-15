package com.mascara.electronicstoremanage.common.interfaces;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:01 CH
 * Filename  : RetrieveEntityRequest
 */
public interface RetrieveEntityRequest<ReturnType, EntityType, IdType> {
    ReturnType retrieveById(IdType entityId);

    List<ReturnType> retrieveAll(EntityType request);
}
