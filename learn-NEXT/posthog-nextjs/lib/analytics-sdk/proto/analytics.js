/*eslint-disable block-scoped-var, id-length, no-control-regex, no-magic-numbers, no-prototype-builtins, no-redeclare, no-shadow, no-var, sort-vars*/
"use strict";

var $protobuf = require("protobufjs/minimal");

// Common aliases
var $Reader = $protobuf.Reader, $Writer = $protobuf.Writer, $util = $protobuf.util;

// Exported root namespace
var $root = $protobuf.roots["default"] || ($protobuf.roots["default"] = {});

$root.analytics = (function() {

    /**
     * Namespace analytics.
     * @exports analytics
     * @namespace
     */
    var analytics = {};

    analytics.Event = (function() {

        /**
         * Properties of an Event.
         * @memberof analytics
         * @interface IEvent
         * @property {string|null} [messageId] Event messageId
         * @property {string|null} [timestamp] Event timestamp
         * @property {string|null} [event] Event event
         * @property {string|null} [distinctId] Event distinctId
         * @property {Object.<string,string>|null} [properties] Event properties
         */

        /**
         * Constructs a new Event.
         * @memberof analytics
         * @classdesc Represents an Event.
         * @implements IEvent
         * @constructor
         * @param {analytics.IEvent=} [properties] Properties to set
         */
        function Event(properties) {
            this.properties = {};
            if (properties)
                for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                    if (properties[keys[i]] != null)
                        this[keys[i]] = properties[keys[i]];
        }

        /**
         * Event messageId.
         * @member {string} messageId
         * @memberof analytics.Event
         * @instance
         */
        Event.prototype.messageId = "";

        /**
         * Event timestamp.
         * @member {string} timestamp
         * @memberof analytics.Event
         * @instance
         */
        Event.prototype.timestamp = "";

        /**
         * Event event.
         * @member {string} event
         * @memberof analytics.Event
         * @instance
         */
        Event.prototype.event = "";

        /**
         * Event distinctId.
         * @member {string} distinctId
         * @memberof analytics.Event
         * @instance
         */
        Event.prototype.distinctId = "";

        /**
         * Event properties.
         * @member {Object.<string,string>} properties
         * @memberof analytics.Event
         * @instance
         */
        Event.prototype.properties = $util.emptyObject;

        /**
         * Creates a new Event instance using the specified properties.
         * @function create
         * @memberof analytics.Event
         * @static
         * @param {analytics.IEvent=} [properties] Properties to set
         * @returns {analytics.Event} Event instance
         */
        Event.create = function create(properties) {
            return new Event(properties);
        };

        /**
         * Encodes the specified Event message. Does not implicitly {@link analytics.Event.verify|verify} messages.
         * @function encode
         * @memberof analytics.Event
         * @static
         * @param {analytics.IEvent} message Event message or plain object to encode
         * @param {$protobuf.Writer} [writer] Writer to encode to
         * @returns {$protobuf.Writer} Writer
         */
        Event.encode = function encode(message, writer) {
            if (!writer)
                writer = $Writer.create();
            if (message.messageId != null && Object.hasOwnProperty.call(message, "messageId"))
                writer.uint32(/* id 1, wireType 2 =*/10).string(message.messageId);
            if (message.timestamp != null && Object.hasOwnProperty.call(message, "timestamp"))
                writer.uint32(/* id 2, wireType 2 =*/18).string(message.timestamp);
            if (message.event != null && Object.hasOwnProperty.call(message, "event"))
                writer.uint32(/* id 3, wireType 2 =*/26).string(message.event);
            if (message.distinctId != null && Object.hasOwnProperty.call(message, "distinctId"))
                writer.uint32(/* id 4, wireType 2 =*/34).string(message.distinctId);
            if (message.properties != null && Object.hasOwnProperty.call(message, "properties"))
                for (var keys = Object.keys(message.properties), i = 0; i < keys.length; ++i)
                    writer.uint32(/* id 5, wireType 2 =*/42).fork().uint32(/* id 1, wireType 2 =*/10).string(keys[i]).uint32(/* id 2, wireType 2 =*/18).string(message.properties[keys[i]]).ldelim();
            return writer;
        };

        /**
         * Encodes the specified Event message, length delimited. Does not implicitly {@link analytics.Event.verify|verify} messages.
         * @function encodeDelimited
         * @memberof analytics.Event
         * @static
         * @param {analytics.IEvent} message Event message or plain object to encode
         * @param {$protobuf.Writer} [writer] Writer to encode to
         * @returns {$protobuf.Writer} Writer
         */
        Event.encodeDelimited = function encodeDelimited(message, writer) {
            return this.encode(message, writer).ldelim();
        };

        /**
         * Decodes an Event message from the specified reader or buffer.
         * @function decode
         * @memberof analytics.Event
         * @static
         * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
         * @param {number} [length] Message length if known beforehand
         * @returns {analytics.Event} Event
         * @throws {Error} If the payload is not a reader or valid buffer
         * @throws {$protobuf.util.ProtocolError} If required fields are missing
         */
        Event.decode = function decode(reader, length, error) {
            if (!(reader instanceof $Reader))
                reader = $Reader.create(reader);
            var end = length === undefined ? reader.len : reader.pos + length, message = new $root.analytics.Event(), key, value;
            while (reader.pos < end) {
                var tag = reader.uint32();
                if (tag === error)
                    break;
                switch (tag >>> 3) {
                case 1: {
                        message.messageId = reader.string();
                        break;
                    }
                case 2: {
                        message.timestamp = reader.string();
                        break;
                    }
                case 3: {
                        message.event = reader.string();
                        break;
                    }
                case 4: {
                        message.distinctId = reader.string();
                        break;
                    }
                case 5: {
                        if (message.properties === $util.emptyObject)
                            message.properties = {};
                        var end2 = reader.uint32() + reader.pos;
                        key = "";
                        value = "";
                        while (reader.pos < end2) {
                            var tag2 = reader.uint32();
                            switch (tag2 >>> 3) {
                            case 1:
                                key = reader.string();
                                break;
                            case 2:
                                value = reader.string();
                                break;
                            default:
                                reader.skipType(tag2 & 7);
                                break;
                            }
                        }
                        message.properties[key] = value;
                        break;
                    }
                default:
                    reader.skipType(tag & 7);
                    break;
                }
            }
            return message;
        };

        /**
         * Decodes an Event message from the specified reader or buffer, length delimited.
         * @function decodeDelimited
         * @memberof analytics.Event
         * @static
         * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
         * @returns {analytics.Event} Event
         * @throws {Error} If the payload is not a reader or valid buffer
         * @throws {$protobuf.util.ProtocolError} If required fields are missing
         */
        Event.decodeDelimited = function decodeDelimited(reader) {
            if (!(reader instanceof $Reader))
                reader = new $Reader(reader);
            return this.decode(reader, reader.uint32());
        };

        /**
         * Verifies an Event message.
         * @function verify
         * @memberof analytics.Event
         * @static
         * @param {Object.<string,*>} message Plain object to verify
         * @returns {string|null} `null` if valid, otherwise the reason why it is not
         */
        Event.verify = function verify(message) {
            if (typeof message !== "object" || message === null)
                return "object expected";
            if (message.messageId != null && message.hasOwnProperty("messageId"))
                if (!$util.isString(message.messageId))
                    return "messageId: string expected";
            if (message.timestamp != null && message.hasOwnProperty("timestamp"))
                if (!$util.isString(message.timestamp))
                    return "timestamp: string expected";
            if (message.event != null && message.hasOwnProperty("event"))
                if (!$util.isString(message.event))
                    return "event: string expected";
            if (message.distinctId != null && message.hasOwnProperty("distinctId"))
                if (!$util.isString(message.distinctId))
                    return "distinctId: string expected";
            if (message.properties != null && message.hasOwnProperty("properties")) {
                if (!$util.isObject(message.properties))
                    return "properties: object expected";
                var key = Object.keys(message.properties);
                for (var i = 0; i < key.length; ++i)
                    if (!$util.isString(message.properties[key[i]]))
                        return "properties: string{k:string} expected";
            }
            return null;
        };

        /**
         * Creates an Event message from a plain object. Also converts values to their respective internal types.
         * @function fromObject
         * @memberof analytics.Event
         * @static
         * @param {Object.<string,*>} object Plain object
         * @returns {analytics.Event} Event
         */
        Event.fromObject = function fromObject(object) {
            if (object instanceof $root.analytics.Event)
                return object;
            var message = new $root.analytics.Event();
            if (object.messageId != null)
                message.messageId = String(object.messageId);
            if (object.timestamp != null)
                message.timestamp = String(object.timestamp);
            if (object.event != null)
                message.event = String(object.event);
            if (object.distinctId != null)
                message.distinctId = String(object.distinctId);
            if (object.properties) {
                if (typeof object.properties !== "object")
                    throw TypeError(".analytics.Event.properties: object expected");
                message.properties = {};
                for (var keys = Object.keys(object.properties), i = 0; i < keys.length; ++i)
                    message.properties[keys[i]] = String(object.properties[keys[i]]);
            }
            return message;
        };

        /**
         * Creates a plain object from an Event message. Also converts values to other types if specified.
         * @function toObject
         * @memberof analytics.Event
         * @static
         * @param {analytics.Event} message Event
         * @param {$protobuf.IConversionOptions} [options] Conversion options
         * @returns {Object.<string,*>} Plain object
         */
        Event.toObject = function toObject(message, options) {
            if (!options)
                options = {};
            var object = {};
            if (options.objects || options.defaults)
                object.properties = {};
            if (options.defaults) {
                object.messageId = "";
                object.timestamp = "";
                object.event = "";
                object.distinctId = "";
            }
            if (message.messageId != null && message.hasOwnProperty("messageId"))
                object.messageId = message.messageId;
            if (message.timestamp != null && message.hasOwnProperty("timestamp"))
                object.timestamp = message.timestamp;
            if (message.event != null && message.hasOwnProperty("event"))
                object.event = message.event;
            if (message.distinctId != null && message.hasOwnProperty("distinctId"))
                object.distinctId = message.distinctId;
            var keys2;
            if (message.properties && (keys2 = Object.keys(message.properties)).length) {
                object.properties = {};
                for (var j = 0; j < keys2.length; ++j)
                    object.properties[keys2[j]] = message.properties[keys2[j]];
            }
            return object;
        };

        /**
         * Converts this Event to JSON.
         * @function toJSON
         * @memberof analytics.Event
         * @instance
         * @returns {Object.<string,*>} JSON object
         */
        Event.prototype.toJSON = function toJSON() {
            return this.constructor.toObject(this, $protobuf.util.toJSONOptions);
        };

        /**
         * Gets the default type url for Event
         * @function getTypeUrl
         * @memberof analytics.Event
         * @static
         * @param {string} [typeUrlPrefix] your custom typeUrlPrefix(default "type.googleapis.com")
         * @returns {string} The default type url
         */
        Event.getTypeUrl = function getTypeUrl(typeUrlPrefix) {
            if (typeUrlPrefix === undefined) {
                typeUrlPrefix = "type.googleapis.com";
            }
            return typeUrlPrefix + "/analytics.Event";
        };

        return Event;
    })();

    return analytics;
})();

module.exports = $root;
