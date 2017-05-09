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
 */
package it.giacomobergami.readingFlinkJson.node;

/**
 * Created by vasistas on 06/05/17.
 */
public class NodeVertexWithNodeUpdate extends Task {

  private transient Job father;
  private transient int pos;

  public NodeVertexWithNodeUpdate(Vertex v, Node n, Job father, int pos) {
    super(v, n);
    this.father = father;
    this.pos = pos;
  }

  @Override
  public Node updateWith(Node node) {
    father.updateRawNode(pos, node);
    return node;
  }
}
