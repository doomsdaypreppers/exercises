'use strict'

var ECMA_SIZES = {
    STRING: 2,
    BOOLEAN: 4,
    NUMBER: 8
};
// var Buffer = require('buffer').Buffer
import { Buffer } from 'buffer';


function sizeOfObject(object) {
    if (object == null) {
        return 0
    }

    var bytes = 0
    for (var key in object) {
        if (!Object.hasOwnProperty.call(object, key)) {
            continue
        }

        bytes += sizeOf(key)
        try {
            bytes += sizeOf(object[key])
        } catch (ex) {
            if (ex instanceof RangeError) {
                // circular reference detected, final result might be incorrect
                // let's be nice and not throw an exception
                bytes = 0
            }
        }
    }

    return bytes
}

/**
 * Main module's entry point
 * Calculates Bytes for the provided parameter
 * @param object - handles object/string/boolean/buffer
 * @returns {*}
 */
export function sizeOf(object) {
    if (Buffer.isBuffer(object)) {
        return object.length
    }

    var objectType = typeof (object)
    switch (objectType) {
        case 'string':
            return object.length * ECMA_SIZES.STRING
        case 'boolean':
            return ECMA_SIZES.BOOLEAN
        case 'number':
            return ECMA_SIZES.NUMBER
        case 'object':
            return sizeOfObject(object)
        default:
            return 0
    }
}