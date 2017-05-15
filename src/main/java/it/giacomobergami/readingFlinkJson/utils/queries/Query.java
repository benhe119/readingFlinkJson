package it.giacomobergami.readingFlinkJson.utils.queries;

import it.giacomobergami.readingFlinkJson.node.IUniformView;

import java.util.function.Function;

/**
 * Defining what a query is
 */
public interface Query<K> extends Function<IUniformView, K> {
}
