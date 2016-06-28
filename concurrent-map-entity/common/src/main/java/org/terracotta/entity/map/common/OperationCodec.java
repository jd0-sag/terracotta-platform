/*
 * The contents of this file are subject to the Terracotta Public License Version
 * 2.0 (the "License"); You may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://terracotta.org/legal/terracotta-public-license.
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 *
 * The Covered Software is Terracotta Platform.
 *
 * The Initial Developer of the Covered Software is
 * Terracotta, Inc., a Software AG company
 */

package org.terracotta.entity.map.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


class OperationCodec {
  public static MapOperation decode(byte[] bytes) throws IOException {
    ObjectInputStream input = new ObjectInputStream(new ByteArrayInputStream(bytes));
    byte type = input.readByte();

    switch (MapOperation.Type.values()[type]) {
      case PUT:
        return PutOperation.readFrom(input);
      case GET:
        return GetOperation.readFrom(input);
      case REMOVE:
        return RemoveOperation.readFrom(input);
      case SIZE:
        return SizeOperation.readFrom(input);
      case CONTAINS_KEY:
        return ContainsKeyOperation.readFrom(input);
      case CONTAINS_VALUE:
        return ContainsValueOperation.readFrom(input);
      case CLEAR:
        return ClearOperation.readFrom(input);
      case PUT_ALL:
        return PutAllOperation.readFrom(input);
      case KEY_SET:
        return KeySetOperation.readFrom(input);
      case VALUES:
        return ValuesOperation.readFrom(input);
      case ENTRY_SET:
        return EntrySetOperation.readFrom(input);
      case PUT_IF_ABSENT:
        return PutIfAbsentOperation.readFrom(input);
      case PUT_IF_PRESENT:
        return PutIfPresentOperation.readFrom(input);
      case CONDITIONAL_REMOVE:
        return ConditionalRemoveOperation.readFrom(input);
      case CONDITIONAL_REPLACE:
        return ConditionalReplaceOperation.readFrom(input);
      default:
        throw new IllegalArgumentException("Unknown map operation type " + type);
    }
  }

  public static byte[] encode(MapOperation operation) throws IOException {
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    ObjectOutputStream output = new ObjectOutputStream(byteOut);

    output.writeByte(operation.operationType().ordinal());
    operation.writeTo(output);

    output.close();
    return byteOut.toByteArray();
  }
}