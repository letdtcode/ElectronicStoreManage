package com.mascara.electronicstoremanage.common.interfaces;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:01 CH
 * Filename  : ModifyEntityRequest
 */
public interface ModifyEntityRequest<CreateType, UpdateType, IdType> {
    IdType insert(CreateType request);

    boolean update(UpdateType request);

    boolean delete(IdType idEntity);
}