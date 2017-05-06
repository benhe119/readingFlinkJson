/*
 * This file is part of readingFlinkJson
 *
 * Copyright (C) 2017 - Giacomo Bergami
 *
 * readingFlinkJson is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * readingFlinkJson is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with readingFlinkJson. If not, see <http://www.gnu.org/licenses/>.
 */package it.giacomobergami.readingFlinkJson.node;

import it.giacomobergami.readingFlinkJson.node.fields.Predecessor;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.CompilerHints;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.Costs;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.Estimates;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.GlobalProperties;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.LocalProperties;

/**
 * Created by vasistas on 06/05/17.
 */
public interface INode {
  int getId();
  String getJid();
  String getType();
  String getPact();
  String getContents();
  String getParallelism();
  String getDriverStrategy();
  GlobalProperties getGlobalProperties();
  LocalProperties getLocalProperties();
  Estimates getEstimates();
  Costs getCosts();
  CompilerHints getCompilerHints();
  Predecessor[] getPredecessors();

  default boolean hasPredecessors() {
    return getPredecessors()!=null && getPredecessors().length > 0;
  }

  default Node updateWith(Node node) {
    node.type = getType();
    node.pact = getPact();
    return node;
  }
}
