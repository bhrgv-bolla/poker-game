package org.bbolla.pokergame.fivecard.configurations;

import java.io.IOException;

/**
 * Interface for a file writer  / database writer to implement
 */
public interface CombinationWriter extends AutoCloseable {

    void saveCombination(CombinationRecord cards) throws IOException;

    void init() throws IOException;
}
