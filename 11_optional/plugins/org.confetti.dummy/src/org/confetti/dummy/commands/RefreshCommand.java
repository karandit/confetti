package org.confetti.dummy.commands;

import static java.util.Optional.empty;
import static java.util.Optional.of;

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
        DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
        ConfettiPlugin.getDefault().setDataProvider(null, empty());
        ConfettiPlugin.getDefault().setDataProvider(dp, of(dp));
        
        return null;
    }

}
