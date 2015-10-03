package org.confetti.dummy.commands;

import static java.util.Optional.empty;

import java.util.Optional;

import org.confetti.core.DataPersister;
import org.confetti.core.DataProvider;
import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * @author Gabor Bubla
 */
public class RefreshCommand extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        //dummy action for refreshing the views data
        DataProvider dataProvider = ConfettiPlugin.getDefault().getDataProvider().getValue();
        Optional<DataPersister> dataPersister = ConfettiPlugin.getDefault().getDataPersister();
        ConfettiPlugin.getDefault().setDataProvider(null, empty());
        ConfettiPlugin.getDefault().setDataProvider(dataProvider, dataPersister);
        
        return null;
    }

}
