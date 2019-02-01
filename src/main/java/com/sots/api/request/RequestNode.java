package com.sots.api.request;

import com.sots.api.LPRoutedObject;
import com.sots.api.crafting.ICraftingTemplate;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RequestNode {
    private final Type type;
    private final List<RequestNode> supRequests = new ArrayList<>();
    private final IRequestLogger logger;
    @Nullable
    private final RequestNode parentNode;
    private final RequestNode root;
    @Nullable
    private final ICraftingTemplate template;
    private final List<LPRoutedObject> onRoute = new ArrayList<>();
    private final UUID ID;
    public RequestNode(Type type, @Nullable IRequestLogger logger, @Nullable RequestNode parentNode, @Nullable ICraftingTemplate template) {
        this.type = type;
        this.logger = logger;
        this.parentNode = parentNode;

        this.root = this;
        this.template = template;
        this.ID = UUID.randomUUID();
    }

    public boolean isRoot() {
        return this == root;
    }

    public Type getType() {
        return type;
    }

    public List<RequestNode> getSupRequests() {
        return supRequests;
    }

    @Nullable
    public RequestNode getParentNode() {
        return parentNode;
    }

    public RequestNode getRoot() {
        return root;
    }

    @Nullable
    public ICraftingTemplate getTemplate() {
        return template;
    }

    public List<LPRoutedObject> getOnRoute() {
        return onRoute;
    }

    public UUID getID() {
        return ID;
    }

    public void addSupRequest(RequestNode node) {
        node.supRequests.add(node);
    }

    public void cancel() {
        if (isRoot()) {
            cancelIntarnel();
        } else getRoot().cancel();
    }

    protected void cancelIntarnel() {
        supRequests.forEach(RequestNode::cancelIntarnel);
        onRoute.forEach(LPRoutedObject::cancel);
    }

    public IRequestLogger getLogger() {
        if (!isRoot())
            return getRoot().getLogger();
        return logger;
    }

    enum Type {
        CRAFT,
        SUPPLY
    }
}