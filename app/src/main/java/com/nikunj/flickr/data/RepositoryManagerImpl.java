package com.nikunj.flickr.data;

import javax.inject.Inject;

/**
 * Created by nikunj on 6/6/18.
 */
public class RepositoryManagerImpl implements RepositoryManager {

    private ServiceNetwork serviceNetwork;

    @Inject
    public RepositoryManagerImpl(ServiceNetwork serviceNetwork) {
        this.serviceNetwork = serviceNetwork;
    }

    @Override
    public ServiceNetwork getServiceNetwork() {
        return serviceNetwork;
    }
}
