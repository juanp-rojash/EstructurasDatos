package com.example.Util.Factory;

import com.example.Util.QuickFindUF;
import com.example.Util.QuickUnionUF;
import com.example.Util.WeightedQuickUnionUF;
import com.example.Util.Interface.UnionFind;

public class UnionFindFactory {
    public enum UnionFindType {
        QUICK_FIND,
        QUICK_UNION,
        WEIGHTED_QUICK_UNION
    }

    public static UnionFind createUnionFind(UnionFindType type, int size) {
        switch (type) {
            case QUICK_FIND:
                return new QuickFindUF(size);
            case QUICK_UNION:
                return new QuickUnionUF(size);
            case WEIGHTED_QUICK_UNION:
                return new WeightedQuickUnionUF(size);
            default:
                throw new IllegalArgumentException("Unknown UnionFind type");
        }
    }
}