/*
 * Copyright (c) 2014 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.controller.md.cluster.datastore.model;

import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.data.api.InstanceIdentifier;
import org.opendaylight.yangtools.yang.data.api.schema.MapEntryNode;
import org.opendaylight.yangtools.yang.data.api.schema.MapNode;
import org.opendaylight.yangtools.yang.data.api.schema.NormalizedNode;
import org.opendaylight.yangtools.yang.data.impl.schema.ImmutableNodes;
import org.opendaylight.yangtools.yang.data.impl.schema.builder.api.CollectionNodeBuilder;
import org.opendaylight.yangtools.yang.data.impl.schema.builder.impl.ImmutableContainerNodeBuilder;
import org.opendaylight.yangtools.yang.data.impl.schema.builder.impl.ImmutableMapNodeBuilder;

public class CarsModel {
    public static final QName BASE_QNAME = QName.create("urn:opendaylight:params:xml:ns:yang:controller:md:sal:dom:store:test:cars", "2014-03-13",
        "cars");

    public static final InstanceIdentifier BASE_PATH = InstanceIdentifier.of(BASE_QNAME);

    public static final QName CARS_QNAME = QName.create(BASE_QNAME, "cars");
    public static final QName CAR_QNAME = QName.create(CARS_QNAME, "car");
    public static final QName CAR_NAME_QNAME = QName.create(CAR_QNAME, "name");
    public static final QName CAR_PRICE_QNAME = QName.create(CAR_QNAME, "price");


    public static NormalizedNode create(){

        // Create a list builder
        CollectionNodeBuilder<MapEntryNode, MapNode> cars =
            ImmutableMapNodeBuilder.create().withNodeIdentifier(
                new InstanceIdentifier.NodeIdentifier(
                    QName.create(CARS_QNAME, "car")));

        // Create an entry for the car altima
        MapEntryNode altima =
            ImmutableNodes.mapEntryBuilder(CARS_QNAME, CAR_NAME_QNAME, "altima")
                .withChild(ImmutableNodes.leafNode(CAR_NAME_QNAME, "altima"))
                .withChild(ImmutableNodes.leafNode(CAR_PRICE_QNAME, 1000))
                .build();

        // Create an entry for the car accord
        MapEntryNode honda =
            ImmutableNodes.mapEntryBuilder(CARS_QNAME, CAR_NAME_QNAME, "accord")
                .withChild(ImmutableNodes.leafNode(CAR_NAME_QNAME, "accord"))
                .withChild(ImmutableNodes.leafNode(CAR_PRICE_QNAME, 2000))
                .build();

        cars.withChild(altima);
        cars.withChild(honda);

        return ImmutableContainerNodeBuilder.create()
            .withNodeIdentifier(new InstanceIdentifier.NodeIdentifier(BASE_QNAME))
            .withChild(cars.build())
            .build();

    }

    public static NormalizedNode emptyContainer(){
        return ImmutableContainerNodeBuilder.create()
            .withNodeIdentifier(new InstanceIdentifier.NodeIdentifier(BASE_QNAME))
            .build();
    }

}
